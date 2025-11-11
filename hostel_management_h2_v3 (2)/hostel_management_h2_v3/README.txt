
Hostel Management System v3 (Spring Boot + JSP + H2)
----------------------------------------------------
Features:
- Login/Signup (session auth). Default user: admin/admin
- Students, Rooms, Fees, Attendance, Complaints
- Upsert behavior for Fees (due->paid), Attendance (same date), Complaints (per student)
- Validation: operations on non-existent student ID are blocked with clear messages
- Persistent H2 database (file ./data/hostel_db)

Run:
1) mvn clean spring-boot:run
2) Open http://localhost:8081/
3) Login with admin/admin
H2 Console: http://localhost:8081/h2-console (JDBC URL: jdbc:h2:file:./data/hostel_db)
