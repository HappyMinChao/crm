#条件查询

select * from (
          select rownum rn,
                c.id cid,
                c.no cno,
                c.name cname,
                c.region cregion,
                c.customer_level ccuslevel,
                c.state cstate,
                sc.contact sccon
          from customers c
              left outer join sales_chances sc
              on c.manager_id = sc.id
     )
where     cname like '%恒大电脑%' 
      and cregion like '%北京%'
      and sccon like '%陈雷%' 
      and ccuslevel like '%战略合作伙伴%'
      and cstate like '%删除%'
      
#分页查询客户信息

select cid, cno,cname,cregion,sccon,ccuslevel
		from (
				select rownum rn,
						   c.id cid
        				c.no cno,
                c.state cstate,
        				c.name cname,
        				c.region cregion,
        				c.customer_level ccuslevel,
        				sc.contact sccon
				from customers c
        		left outer join sales_chances sc
        		on c.manager_id = sc.id
			 )

where rn >=3 and rn<6

#查询该用户的联系人
select count(*) from contacts con where con.customer_id = 2

#查询为分配的服务

select csid, cstype,cstitle,csdate,uname,cname,csallotid
      from  (select rownum rn, 
              cs.id csid,
              cs.service_type cstype,
              cs.service_title cstitle,
              cs.create_date csdate,
              cs.allot_id csallotid ,
              u.id uuuuid,
              u.name uname,
              c.id cid,
              c.name cname
            from customer_services cs
            left outer join customers c
            on c.id = cs.customer_id
            left outer join users u
            on u.id = cs.created_id
            where cs.allot_id is null
        )
 where rn >= 2 and rn < 5
 
 
 #带条件查询服务信息
 
select csid, cstype,cstitle,csdate,uname,cname
from  (select rownum rn, 
        cs.id csid,
        cs.service_type cstype,
        cs.service_title cstitle,
        cs.create_date csdate,
        u.id uuuuid,
        u.name uname,
        c.id cid,
        c.name cname
      from customer_services cs
      left outer join contacts c
        on c.id = cs.customer_id
        left outer join users u
        on u.id = cs.created_id
        where cs.allot_id is null
        and cs.service_type like '投诉'
        and cs.service_title like '%a%'
        and cs.customer_id in (select id from contacts c where c.name like '%杨%')
        and to_char(cs.create_date,'yyyy-MM-DD') between '2016-03-30' and '2016-03-31'
)
 where rn >= 1 and rn < 2
 
#分页显示customer_drains cd
select * from (
    select  rownum rn,
            cd.id,
            cd.delay, 
            cd.drain_date,
            cd.last_order_date,
            cd.reason,
            cd.status,
            cd.customer_id,
            c.name cname,
            con.name conName
    from customer_drains cd
    left outer join customers c
    on c.id = cd.customer_id
    left outer join contacts con
    on c.manager_id = con.id
)
where rn >=1 and rn < 2

#模糊查询总记录数
select count(*) 
from customer_drains cd
left outer join customers c
on c.id = cd.customer_id
left outer join contacts con
on c.manager_id = con.id
WHERE  c.name like '%阿%'  and con.name like '%杨%' 
#选择根据id查询customer_drains   
select  rownum rn,
        cd.id,
        cd.delay, 
        cd.drain_date,
        cd.last_order_date,
        cd.reason,
        cd.status,
        cd.customer_id,
        c.name cname,
		con.name conName
from customer_drains cd
left outer join customers c
on c.id = cd.customer_id
left outer join contacts con
on c.manager_id = con.id
where cd.id = 204

#
