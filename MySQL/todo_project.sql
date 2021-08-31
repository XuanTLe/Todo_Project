create database todo_project;

use todo_project;

describe to_do;

insert to_do(completed, description, due_date)
	values('yes', 'grocery', '08/30/2021');
insert to_do(completed, description, due_date)
	values('yes', 'walk the dog', '08/31/2021');
insert to_do(completed, description, due_date)
	values('yes', 'exercise', '09/1/2021');
insert to_do(completed, description, due_date, user_id)
	values('no', 'marathon', '09/2/2021', 1);
insert to_do(completed, description, due_date, user_id)
	values('no', 'cook', '09/3/2021', 1);
insert to_do(completed, description, due_date, user_id)
	values('yes', 'exercise', '09/1/2021', 2);
insert to_do(completed, description, due_date, user_id)
	values('yes', 'project', '09/1/2021', 2);
insert to_do(completed, description, due_date, user_id)
	values('yes', 'meeting', '09/1/2021', 3);
insert to_do(completed, description, due_date, user_id)
	values('no', 'walking', '09/7/2021', 3);
insert to_do(completed, description, due_date, user_id)
	values('yes', 'cleanning', '09/8/2021', 5);
describe user;

insert user(password, user_name)
	values('nyxwl', 'Max');
insert user(password, user_name)
	values('nyfgl', 'Bethany');
insert user(password, user_name)
	values('llxwlmm', 'James');
insert user(password, user_name)
	values('nokokokkk', 'Cindy');
insert user(password, user_name)
	values('kmkd', 'John');


select * from user;
select * from to_do;