insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz,jiaoyzh) 
values( 'CheckClerk' , 'complexQuery' , '1' ,'selectClerk,selectClerkjues,addClerk,updateClerk,addjues,deletejues' ,  'AD域登录' ,'selectClerk,selectClerkjues,addClerk,updateClerk,addjues,deletejues');


---------
insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) 
values( 'selectClerk' , 'simpleQuery' , '1' , 'select clerknum , clerkpwd , n_organnum from clerktable where clerknum = :clerknum' , '查询clerk');

insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) 
values( 'selectClerkjues' , 'simpleQuery' , '1' , 'select * from guiyjsgxb where guiyid = :guiyid and juesid = :juesid' , '查询clerk 角色');

insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) 
values( 'addClerk' , 'simpleTrans' , '1' , 'insert into clerktable(CLERKNUM , clerkname , clerkpwd , n_organnum , shenghjgh ,wdflag ) values(:clerknum ,:clerkname , :clerkpwd ,:n_organnum , :shenghjgh ,:wdflag )' , '添加clerk');

insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) 
values( 'updateClerk' , 'simpleTrans' , '1' , 'update clerktable set clerkpwd  = :clerkpwd , postnum  = :postnum , postname  = :postname , n_updatedate  = :n_updatedate , n_organnum  = :n_organnum , n_error  = :n_error , n_logdate  = :n_logdate , n_ip  = :n_ip , n_creator  = :n_creator , shenghjgh  = :shenghjgh , wdflag  = :wdflag  where clerkname = :clerkname' , '更新clerk');

insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) 
values( 'addjues' , 'simpleTrans' , '1' , 'insert into guiyjsgxb values (:guiyid , :juesid , :beiz )' , '添加clerk角色');

insert into yq_jiaoynr(jiaoyid , jiaoylx , jiaoyzt , jiaoynr , jiaoybz) 
values( 'deletejues' , 'simpleTrans' , '1' , 'delete guiyjsgxb where guiyid = :guiyid and juesid = :juesid ' , '删除clerk角色');