create table template(
	tid int not null identity(1,1) primary key,
	temp_name varchar(30) not null constraint TemplateCK unique,
	temp_description varchar(80)
);

create table checklist(
	cid int not null identity(1, 1) primary key,
	check_name varchar(30) not null constraint CheckListCK unique,
	check_description varchar(80),
	check_duedate date,
	completed bit,
	
	tid int foreign key references template on delete set null
);

create table task(
	lid int not null identity(1, 1) primary key,
	task_name varchar(30) not null constraint TaskCK unique,
	task_description varchar (80)
);

create table task_check(
	task_duedate date,
	isClosed bit,

	lid int not null foreign key references task on delete cascade,
	cid int not null foreign key references checklist on delete cascade
);

create table task_template(
	lid int not null foreign key references task on delete cascade,
	tid int not null foreign key references template on delete cascade
);

create table tag(
	gid int not null identity(1,1) primary key,
	tag_name varchar(30) not null unique,
	color int
);

create table tags_checklists(
	cid int not null foreign key references checklist on delete cascade,
	gid int not null foreign key references tag on delete cascade,
	primary key (gid, cid)
);