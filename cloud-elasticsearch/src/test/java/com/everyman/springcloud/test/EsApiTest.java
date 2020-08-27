package com.everyman.springcloud.test;

import com.alibaba.fastjson.JSON;
import com.everyman.springcloud.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhougang
 * @date 2020/08/26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EsApiTest
{

    @Resource
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Test
    public void testCreateIndex() throws IOException
    {

        CreateIndexRequest request = new CreateIndexRequest("everyman_index");
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("createIndexResponse = " + createIndexResponse);
    }

    @Test
    public void testGetIndex() throws IOException
    {
        GetIndexRequest getIndexRequest = new GetIndexRequest("everyman_index");
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println("exists = " + exists);
    }

    @Test
    public void testDeleteIndex() throws IOException
    {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("everyman_index");
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println("delete = " + delete.isAcknowledged());
    }

    @Test
    public void testAddDocument() throws IOException
    {
        /**
         * PUT /everyman_index/doc_1/1
         * {
         *   "name":"zhougang",
         *   "age" : 25,
         * }
         */
        IndexRequest request = new IndexRequest("everyman_index");
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        request.source(JSON.toJSONString(new User("zhougang", 25)), XContentType.JSON);

        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println("indexResponse.status() = " + indexResponse.status());
    }

    @Test
    public void testGetDocument() throws IOException
    {
        /**
         * GET /everyman_index/doc_1/1
         */
        GetRequest request = new GetRequest("everyman_index", "1");
        GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
        if (getResponse.isExists())
        {
            System.out.println("getResponse = " + getResponse.getSource());
        }
    }

    @Test
    public void testUpdateDocument() throws IOException
    {
        /**
         * POST /everyman_index/doc_1/1/_update
         */
        UpdateRequest request = new UpdateRequest("everyman_index", "1");
        request.timeout("1s");

        request.doc(JSON.toJSONString(new User("everyman", 25)), XContentType.JSON);
        UpdateResponse update = client.update(request, RequestOptions.DEFAULT);
        System.out.println("update.status() = " + update.status());
    }

    @Test
    public void testDeleteDocument() throws IOException
    {
        DeleteRequest request = new DeleteRequest("everyman_index", "1");
        request.timeout("1s");

        DeleteResponse delete = client.delete(request, RequestOptions.DEFAULT);
        System.out.println("delete.status() = " + delete.status());
    }

    @Test
    public void testBulkDocument() throws IOException
    {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<User> arrayList = new ArrayList<>();
        arrayList.add(new User("zhangsan", 21));
        arrayList.add(new User("lisi", 23));
        arrayList.add(new User("wangwu", 43));

        for (User user : arrayList)
        {
            bulkRequest.add(new IndexRequest("everyman_index").source(JSON.toJSONString(user), XContentType.JSON));
        }

        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("bulk.hasFailures() = " + bulk.hasFailures());
        System.out.println("bulk.status() = " + bulk.status());
    }


    @Test
    public void testSearch() throws IOException
    {
        /**
         * GET everyman_index/_doc/_search
         * {
         *   "query": {
         *     "match": {
         *       "name": "zhangsan"
         *     }
         *   },
         *   "highlight": {
         *     "pre_tags": "<p>",
         *     "post_tags": "</p>",
         *     "fields": {
         *       "name": {}
         *     }
         *   }
         * }
         */
        SearchRequest searchRequest = new SearchRequest("everyman_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<p>");
        highlightBuilder.postTags("</p>");
        // 多个高亮显示
        highlightBuilder.requireFieldMatch(false);
        searchSourceBuilder.highlighter(highlightBuilder);

        // 查找全部
        // searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        // 条件查询
        searchSourceBuilder.query(QueryBuilders.termQuery("name", "zhangsan"));
        // 进行分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("searchResponse.status() = " + searchResponse.status());
        System.out.println("hits = " + JSON.toJSONString(searchResponse.getHits()));

        System.err.println("===================== ");
        for (SearchHit hit : searchResponse.getHits().getHits())
        {
            System.out.println("hit.getSourceAsMap() = " + hit.getSourceAsMap());

            // 获取高亮
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (null != highlightFields)
            {
                HighlightField name = highlightFields.get("name");
                if (null != name)
                {
                    Text[] fragments = name.fragments();
                    System.out.println("fragments = " + Arrays.toString(fragments));
                }
            }

        }

    }
}

