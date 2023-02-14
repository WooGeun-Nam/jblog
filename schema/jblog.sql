show tables;

desc user;

desc blog;

desc category;

desc post;

-- /assets/upload-images/DefaultBlogProfile.png

select * from blog;

insert into user values(id, name, password, now());

insert into blog values(id, 'id님의 블로그', '/assets/upload-images/DefaultBlogProfile.png');

insert into category values(null, '기본', id);

select now();

select id, title, profile from blog where id = 'dooly';

select * from category;

select * from category;
select no, name from category where id = 'dooly';

select no from category where name = '기본' and id = 'michol';

select no, title, contents, reg_date as regDate from post where category_no = 1;

select no, title, contents, date_format(reg_date, '%Y/%m/%d') as regDate from post where category_no = 1;

update blog set title = '둘리 이름 바꿈' where id = 'dooly';

select * from category;

insert into post values(null, '둘리 첫글 테스트', '후루룩후루룩', now(), 1);

select * from category;

alter table category add column default_view enum("Y", "N") default "N" after id;

update category set default_view = 'Y' where name = '기본';

select * from category where id = 'gildong';

select * from post;

			select no, title, contents, date_format(reg_date, '%Y/%m/%d') as regDate 
			from post 
			where category_no = 1
			order by no desc
            limit 0,1;
            
select no, name, (select count(*) from post where category_no = c.no) as count, default_view from category c where id = 'dooly' order by field(default_view,'Y','N'), no desc;

			select no, name, (select count(*) from post where category_no = c.no) as count, default_view 
			from category c 
			where id = 'dooly'
			order by field(default_view,'Y','N'), no desc;

select count(*) from post where category_no = 1;

select * from category;

desc category;

select no from category where id='dooly' and name='기본';

select * from post where category_no = 1;

select * from post where category_no = (select no from category where id='dooly' and name='기본');

select p.no, title, contents, reg_date from post p join category c on p.category_no = c.no where id='dooly' and name='기본';

select no from category where name = '둘리의 추가 카테고리' and id='dooly' ;

desc category;

select * from post where category_no = (select no from category where name = '둘리의 추가 카테고리' and id='dooly') order by no desc limit 0,1;

select p.no, title, contents, reg_date
from post p 
join category c 
on p.category_no = c.no 
where c.name = '둘리의 추가 카테고리' and c.id='dooly'
order by no desc
limit 0,1;