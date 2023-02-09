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

select name from category where id = 'dooly' and default_view = 'Y';

select * from post;

			select no, title, contents, date_format(reg_date, '%Y/%m/%d') as regDate 
			from post 
			where category_no = 1
			order by no desc
            limit 0,1;
            
select no, name, (select count(*) from post where category_no = c.no) as count, default_view from category c where id = 'dooly' order by field(default_view,'Y','N'), no desc;

select count(*) from post where category_no = 1;
