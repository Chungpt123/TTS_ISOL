create table faculty(
    id number primary key,
    name nvarchar2(30) not null
);
-- subject (Môn h?c)
create table subject(
	id number primary key,
	name nvarchar2(100) not null,
	lesson_quantity number(2,0) not null -- t?ng s? ti?t h?c
);

-- student (Sinh viên)
create table student (
	id number primary key,
	name nvarchar2(30) not null,
	gender nvarchar2(10) not null, -- gi?i tính
	birthday date not null,
	hometown nvarchar2(100) not null, -- quê quán
	scholarship number, -- h?c b?ng
	faculty_id number not null constraint faculty_id references faculty(id) -- mã khoa
);

-- exam management (B?ng ?i?m)
create table exam_management(
	id number primary key,
	student_id number not null constraint student_id references student(id),
	subject_id number not null constraint subject_id references subject(id),
	number_of_exam_taking number not null, -- s? l?n thi (thi trên 1 l?n ???c g?i là thi l?i) 
	mark number(4,2) not null -- ?i?m
);
/* II. INSERT SAMPLE DATA */

/* II. INSERT SAMPLE DATA */

-- subject
insert into subject (id, name, lesson_quantity) values (1, n'Cơ sở dữ liệu', 45);
insert into subject values (2, n'Trí tuệ nhân tạo', 45);
insert into subject values (3, n'Truyền tin', 45);
insert into subject values (4, n'Đồ họa', 60);
insert into subject values (5, n'Văn phạm', 45);


-- faculty
insert into faculty values (1, n'Anh - Văn');
insert into faculty values (2, n'Tin học');
insert into faculty values (3, n'Triết học');
insert into faculty values (4, n'Vật lý');


-- student
insert into student values (1, n'Nguyễn Thị Hải', n'Nữ', to_date('19900223', 'YYYYMMDD'), 'Hà Nội', 130000, 2);
insert into student values (2, n'Trần Văn Chính', n'Nam', to_date('19921224', 'YYYYMMDD'), 'Bình Định', 150000, 4);
insert into student values (3, n'Lê Thu Yến', n'Nữ', to_date('19900221', 'YYYYMMDD'), 'TP HCM', 150000, 2);
insert into student values (4, n'Lê Hải Yến', n'Nữ', to_date('19900221', 'YYYYMMDD'), 'TP HCM', 170000, 2);
insert into student values (5, n'Trần Anh Tuấn', n'Nam', to_date('19901220', 'YYYYMMDD'), 'Hà Nội', 180000, 1);
insert into student values (6, n'Trần Thanh Mai', n'Nữ', to_date('19910812', 'YYYYMMDD'), 'Hải Phòng', null, 3);
insert into student values (7, n'Trần Thị Thu Thủy', n'Nữ', to_date('19910102', 'YYYYMMDD'), 'Hải Phòng', 10000, 1);


-- exam_management
insert into exam_management values (1, 1, 1, 1, 3);
insert into exam_management values (2, 1, 1, 2, 6);
insert into exam_management values (3, 1, 2, 2, 6);
insert into exam_management values (4, 1, 3, 1, 5);
insert into exam_management values (5, 2, 1, 1, 4.5);
insert into exam_management values (6, 2, 1, 2, 7);
insert into exam_management values (7, 2, 3, 1, 10);
insert into exam_management values (8, 2, 5, 1, 9);
insert into exam_management values (9, 3, 1, 1, 2);
insert into exam_management values (10, 3, 1, 2, 5);
insert into exam_management values (11, 3, 3, 1, 2.5);
insert into exam_management values (12, 3, 3, 2, 4);
insert into exam_management values (13, 4, 5, 2, 10);
insert into exam_management values (14, 5, 1, 1, 7);
insert into exam_management values (15, 5, 3, 1, 2.5);
insert into exam_management values (16, 5, 3, 2, 5);
insert into exam_management values (17, 6, 2, 1, 6);
insert into exam_management values (18, 6, 4, 1, 10);



/*================================================*/
 /********* A. BASIC QUERY *********/
-- Câu 1
--a. id t?ng d?n
SELECT *FROM STUDENT 
ORDER BY id ASC
--b. gi?i tính
SELECT *FROM STUDENT 
ORDER BY gender ASC
--c. ngày sinh t?ng d?n và h?c b?ng t?ng d?n
SELECT *FROM STUDENT 
ORDER BY birthday ASC, scholarship DESC;
--Câu 2
SELECT *FROM subject
where name like ('T%')
--Câu 3
SELECT *FROM student
where name like ('%i')
--Câu 4
SELECT *FROM faculty
where name like ('_n%')
--Câu 5
SELECT *FROM student
where name like ('%Th?%')
--Câu 6
SELECT *FROM student
where name like ('[a-m]%')
ORDER BY name ASC
--Câu 7
SELECT *FROM STUDENT
where scholarship > 100000
ORDER BY faculty_id DESC

--Câu 8
SELECT *FROM STUDENT
WHERE hometown = n'Hà N?i'
ORDER BY scholarship DESC
--Câu 9
SELECT *FROM student 
where birthday between '01/01/1991' and '05/06/1992'
--Câu 10
SELECT *FROM student 
where scholarship between 80000 and 150000
whrere
--Câu 11
SELEC *FROM subject
whrere lesson_quantity > 30 AND lesson_quantity < 45

/********* B. CALCULATION QUERY *********/
--1
select id, gender, faculty_id,case when scholarship >500000 then N'Học bổng cao' else N' Mức trung bình' end as "Mức độ"
from student
--2
SELECT COUNT(*) AS "T?ng sinh viên"  FROM STUDENT
--4
SELECT faculty_id, COUNT(id) AS "T?ng sinh viên" From student
GROUP BY faculty_id
--5
SELECT student_id, count(subject_id) AS "S? môn h?c:" FROM exam_management
GROUP BY student_id
--6
SELECT subject_id, count(student_id) AS "S? sinh viên:" FROM exam_management
GROUP BY subject_id
--7
SELECT  faculty_id, AVG(scholarship) AS "T?ng s? h?c b?ng:" FROM student
GROUP BY faculty_id
--8
SELECT  faculty_id, MAX(scholarship) AS "H?c b?ng cao nh?t:" FROM student
GROUP BY faculty_id
SELECT  MAX(scholarship) FROM student

--9
elect faculty_id,sum(case gender when N'nam'then 1 else 0 end),sum(case gender when N'nữ'then 1 else 0 end)
from student
group by faculty_id    
--10
select year(getdate())-year(birthday) as "Tuổi",count(student_id) as "Số sinh viên"
from student
group by (year(getdate())-year(birthday))
--11
select hometown , count(id) as "Số sinh viên"
from student
group by hometown 
having count(id)>=2
--12
select student_id,subject_id,count(number_of_exam_taking) as "so lan thi lai"
from exam_management
group by student_id,subject_id
having count(number_of_exam_taking)>1
--13
select name, gender, number_of_exam_taking, avg(mark) from exam_management a, student b
where a.student_id = b.id and  number_of_exam_taking  = 1 and gender = n'Nam'  
group by name, gender, number_of_exam_taking
having avg(mark) > 7
--14
select student_id, count(subject_id) from exam_management  
where  number_of_exam_taking  = 1 and mark < 4  
group by student_id
having count(subject_id) > 2
--15
select faculty_id, count(id)
from student
where gender = n'Nam'
group by faculty_id 
having count(id) > 2
--16
select faculty_id  ,count(id)
from student
where scholarship  between 200000 and 300000
group by faculty_id 
having count(id) > 2
--17
SELECT *FROM STUDENT
WHERE scholarship = (SELECT MAX(scholarship) FROM student)
/********* C. DATE/TIME QUERY *********/
--1
SELECT *FROM STUDENT
WHERE hometown like 'Hà Nội' and EXTRACT(MONTH FROM BIRTHDAY) = 2
--2
SELECT *FROM student
WHERE EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM BIRTHDAY) > 20

--3
SELECT *FROM student
WHERE EXTRACT(YEAR FROM BIRTHDAY) = 1990 AND (EXTRACT(MONTH FROM BIRTHDAY) in (1,2,3))
/********* D. JOIN QUERY *********/
--1
SELECT *FROM STUDENT INNER JOIN faculty ON
STUDENT.faculty_id = faculty.ID
WHERE
faculty.name like 'Anh - Văn' or faculty.name like 'Vật lý'
--2
SELECT *FROM STUDENT INNER JOIN faculty ON
STUDENT.faculty_id = faculty.ID
WHERE
(faculty.name like 'Anh - Văn' or faculty.name like 'Tin hoc') and gender like 'Nam'
--3
SELECT student.id, student.name,exam_management.subject_id, exam_management.mark FROM student 
        INNER JOIN exam_management on student.id = exam_management.student_id
WHERE exam_management.subject_id = 1 and number_of_exam_taking = 1 and 
exam_management.mark = (SELECT  MAX(mark) from exam_management
where number_of_exam_taking = 1 and exam_management.subject_id = 1)
--4
SELECT *FROM student 
WHERE birthday = (
SELECT MIN(birthday) FROM student
WHERE faculty_id = 1
)
--5
SELECT *FROM(
SELECT faculty.id, count(faculty.id) as "SL" FROM faculty 
INNER JOIN student ON faculty.id = student.faculty_id
GROUP BY faculty.id
ORDER BY SL DESC
)
WHERE ROWNUM = 1
--c2

                
--6
SELECT *FROM (
SELECT faculty_id, count(id) AS "SL" from student
where gender = n'Nữ'
Group by faculty_id
ORDER BY SL DESC
)
WHERE ROWNUM = 1

--7
select student_id,exam_management.subject_id, exam_management.mark  from exam_management,
(select subject_id, max(mark) as maxdiem from exam_management group by subject_id)a
where exam_management.subject_id = a.subject_id and exam_management.mark = a.maxdiem

--8
SELECT *FROM faculty WHERE id not in ( SELECT faculty.id FROM faculty INNER JOIN student ON faculty.id = student.faculty_id)

--9
SELECT *FROM STUDENT WHERE ID NOT IN (
SELECT exam_management.student_id FROM exam_management
INNER JOIN subject ON subject.id = exam_management.subject_id
WHERE subject.name = 'Cơ sở dữ liệu'
)
--10
select student_id from exam_management  em
where number_of_exam_taking  = 2 and not exists 
(select *from exam_management where number_of_exam_taking = 1 and student_id = em.student_id)
