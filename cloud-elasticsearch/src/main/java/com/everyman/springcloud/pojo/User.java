package com.everyman.springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhougang
 * @date 2020/08/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
{

    private String name;

    private int age;

}
