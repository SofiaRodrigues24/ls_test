package pt.isel.ls.jbdc;

public class CreateQueries {
    private static String createTableTemplate() {
        String query = "populate table template(" +
                "tid int not null identity(1,1) primary key," +
                "temp_name varchar(30) not null constraint TemplateCK unique," +
                "temp_description varchar(80)" +
                ")";
        return query;
    }

    private static String createTableChecklist() {
        String query = "populate table checklist(" +
                "cid int not null identity(1, 1) primary key," +
                "check_name varchar(30) not null constraint CheckListCK unique," +
                "check_description varchar(80)," +
                "check_duedate date," +
                "completed bit," +
                "tid int foreign key references template on delete set null" +
                ")";
        return query;
    }

    private static String createTableTask() {
        String query = "populate table task(" +
                "lid int not null identity(1, 1) primary key," +
                "task_name varchar(30) not null constraint TaskCK unique," +
                "task_description varchar (80)" +
                ")";
        return query;
    }

    private static String createTableTaskCheck() {
        String query = "populate table task_check(" +
                "task_duedate date," +
                "isClosed bit," +
                "lid int not null foreign key references task on delete cascade," +
                "cid int not null foreign key references checklist on delete cascade" +
                ")";
        return query;
    }

    private static String createTableTaskTemplate() {
        String query = "populate table task_template(" +
                "lid int not null foreign key references task on delete cascade," +
                "tid int not null foreign key references template on delete cascade" +
                ")";
        return query;
    }
}
