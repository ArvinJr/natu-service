## 那兔网！！！
### 接口数据传输及解密
~~~
1、所有接口方法以参数 String ciphertext来接收所有参数
2、在方法首行添加如下代码，用于参数解密：
        // 解密
        Object object = DecryptUtil.decrypt**(ciphertext, Object.class);
        if(null == object){
            return AjaxResult.error("系统错误，请重试！！");
        }
 其中：Object表示业务代码中需要的数据类型，String、DTO、entity、JSONObject（Long、Integer先用String，再强转）
       DecryptUtil 内有方法 decryptBase64、decryptRsa 和 decryptAes 三个解密方法，根据项目需求选择使用
~~~
### 注解使用参考
~~~
1、@Log：开发后台时，在controller层，除get请求外，在对应方法上添加此注解，用于记录操作日志
    如： @Log(title = "参数管理", businessType = BusinessType.INSERT)
    
2、@RepeatSubmit：用于防止重复提交
      
3、@ApiLog：开发接口时，在controller层，除get请求外，在对应方法上添加此注解，用于记录操作日志
    如： @ApiLog(title = "参数管理", businessType = BusinessType.INSERT)

4、@FieldRepeat：可在新增或修改时使用，用于校验参数是否存在，仅用于后台

5、@AuthRequired：用于校验是否登录，根据具体功能使用，仅开发接口时使用

6、@EncryptResponse：在所有接口方法上添加此注解，用于数据加密后返回

7、@PrimaryKey：标明字段为主键；@ParentKey：标明字段为父id；@ChildrenKey：标明字段为子集
    此三个注解搭配 TreeUtils 使用

8、@ApiCallLog：用于api模块，标识接口属性，记录接口调用日志

注：用于接口端的实体类中,Date类型的字段上添加加如下两个注解
    @JsonFormat(pattern = "xxxxxx")
    @JSONField(format = "xxxxxx")
~~~
### 工具类使用参考
~~~
1、BaseUtils：所有工具类继承一下这个工具类，可以省去定义 org.slf4j.Logger

2、JsonUtil：用于JSON的判断及转换，具体见方法

3、HttpClientUtil：HTTP请求

4、Base64File：用于 Base64 串转 MultipartFile

5、AesUtil：AES加密解密

6、RsaUtil：RSA加密解密

7、QrCodeUtils：二维码的生成及解析

8、ThumbnailsUtils：用于文件的压缩

9、TreeUtils：树状结构的生成

10、TableUtils：用于动态获取表名
~~~
#代码部分规范要求
~~~
1. 不得擅自修改数据库
2. 自己本地的配置文件不要提交
3. .iml文件不要提交
4. IDEA设置
/**
 * @Author: 自己的花名
 * @Date: ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}:${SECOND}
 * @Desc:
 */
5. IDEA设置自动导包和删除无用包
6. 单行不超过80字 方法内原则不超过50行(不包含注释和方法名)
7:不准使用Lombok 不符合低耦合设计思想
8:修改他人方法也需方法上加上本人名称
/**
 * @Author: 自己的花名
 * @Date: ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}:${SECOND}
 * @Desc:
 */
 更多规范参考飞书https://u058ouqqlv.feishu.cn/docx/EIwMdfBD1oZ21lxQvBPcuGglnng   密码：NEy5
~~~