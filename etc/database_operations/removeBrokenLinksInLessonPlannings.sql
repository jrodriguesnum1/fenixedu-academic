delete from LESSON_PLANNING where LESSON_PLANNING.KEY_EXECUTION_COURSE not in (select EXECUTION_COURSE.ID_INTERNAL from EXECUTION_COURSE);