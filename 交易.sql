
--交易6   获取MQ配置参数 
select ip , port , mqlchannel , queuemanager , requestqeue , replyqueue , characterset , username , userpassword from MQconfigure;

--交易1   记录主任务信息
insert into taskzhub(instanceid , managetype , zhangh , limit , cccode , clerkname , clerknum , clerkorgnum ) 
values(:instanceid , :managetype , :zhangh , :limit , :cccode , :clerkname , :clerknum , :clerkorgnum);


--交易2     查询主任务结果表
select instanceid , zhangh , indicator , instruction , noteline , occurrencecount , numberSignature , notoSignature from TASKZJGB;


--交易3     查询主任务结果表副表
select occurrencecount , masterinstanceid , zhangh , limit , ccode , indicator , recordsequence , signaturename , imagekey from TASKJGFB;


--交易4     记录子任务表
insert into TASKZIB(instanceid , masterinstanceid , zhangh , imagekey , limit , cccode , indicator , recordsequence , signaturename ) 
values(:instanceid  , :masterinstanceid , :zhangh , :imagekey , :limit , :cccode , :indicator , :recordsequence , :signaturename );

--交易5     查询子任务结果表
--select instanceid , zhangh , imagekey , limit , ccode , chopindicator , recordsequence , signaturename , image from TASKJGZIB;


select * from TASKJGZIB a left join (select instanceid from TASKZIB where masterinstanceid = :masterinstanceid ) b on a.instanceid = b.instanceid ;


insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) values( 'MQcommunication' , 'complexQuery' , '1' ,'addDad,queryDad,queryDad_vice,addSon,querySon,MQConfigure' ,  'MQ通讯' );
insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) values( 'addDad' , 'simpleQuery' , '1' , 'insert into taskzhub(instanceid , managetype , zhangh , limit , cccode , clerkname , clerknum , clerkorgnum ) values(:instanceid , :managetype , :zhangh , :limit , :cccode , :clerkname , :clerknum , :clerkorgnum);' , '主任务信息记录到主任务表');
insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) values('queryDad' , 'simpleQuery' , '1' , 'select instanceid , zhangh , indicator , instruction , noteline , occurrencecount , numberSignature , notoSignature from TASKZJGB' , '查询主任务结果表');
insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) values('queryDad_vice' , 'simpleQuery' , '1' , 'select occurrencecount , masterinstanceid , zhangh , limit , ccode , indicator , recordsequence , signaturename , imagekey from TASKJGFB' , '查询主任务结果表副表');
insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) values('addSon' , 'simpleQuery' , '1' , 'insert into TASKZIB(instanceid , masterinstanceid , zhangh , imagekey , limit , cccode , indicator , recordsequence , signaturename ) values(:instanceid  , :masterinstanceid , :zhangh , :imagekey , :limit , :cccode , :indicator , :recordsequence , :signaturename )' , '子任务信息记录到子任务表');
insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) values('querySon' , 'simpleQuery' , '1' , 'select * from TASKJGZIB a left join (select instanceid from TASKZIB where masterinstanceid = :masterinstanceid ) b on a.instanceid = b.instanceid ' , '查询子任务结果表');
insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) values('MQConfigure' , 'simpleQuery' , '1' , 'select ip , port , mqlchannel , queuemanager , requestqeue , replyqueue , characterset , username , userpassword from MQconfigure' , '查询MQ连接需要的配置信息');





delete from yq_jiaoynr where jiaoyid = 'querySon';


select  * from yq_jiaoynr where jiaoyid in ('MQcommunication' , 'addDad' , 'queryDad' , 'queryDad_vice' , 'addSon' , 'querySon' , 'MQConfigure'  );


--mq参数
insert  into MQCONFIGURE values('133.10.221.122',8209,'ULCVS01CN.SVRCHL.TM','ULCVS01CN','T.CVS.CN_CVS.CN_HUB.CVS_RQST','T.CVS.CN_HUB.CN_CVS.CVS_RESP',819,null,null);


