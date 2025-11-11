
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><head><title>Rooms</title>
<style>
:root{--bg:#0f172a;--card:#111827;--muted:#e5e7eb;--accent:#4f46e5;}
*{box-sizing:border-box}
body{margin:0;background:linear-gradient(120deg,#0f172a,#111827);font-family:system-ui,Segoe UI,Roboto,Arial;color:#f8fafc;min-height:100vh}
.container{max-width:1000px;margin:32px auto;padding:16px}
.card{background:rgba(17,24,39,.85);backdrop-filter:blur(6px);border:1px solid #1f2937;border-radius:16px;padding:20px;box-shadow:0 10px 30px rgba(0,0,0,.35)}
h1,h2{margin:0 0 12px} a{color:var(--muted);text-decoration:none}
nav{display:flex;gap:12px;flex-wrap:wrap;margin-bottom:16px}
nav a{padding:8px 12px;border-radius:12px;border:1px solid #1f2937}
nav a:hover{border-color:#374151}
form{display:flex;gap:8px;flex-wrap:wrap;margin:12px 0}
input,select,button{padding:10px 12px;border-radius:12px;border:1px solid #374151;background:#0b1220;color:#f3f4f6}
button{background:var(--accent);border:none;color:white;cursor:pointer}
button:hover{opacity:.95}
table{width:100%;border-collapse:collapse;margin-top:12px}
th,td{border-bottom:1px solid #1f2937;padding:10px;text-align:left}
.badge{padding:4px 8px;border-radius:999px;background:#1f2937;border:1px solid #374151}
footer{margin-top:20px;color:#9ca3af;font-size:12px;text-align:center}
.error{color:#fca5a5} .message{color:#86efac}
</style>
</head>
<body><div class="container">
  <div class="card">
    <h2>🚪 Rooms</h2>
    <nav>
      <a href="/">Dashboard</a>
      <a href="/students">Students</a>
      <a href="/fees">Fees</a>
      <a href="/attendance">Attendance</a>
      <a href="/complaints">Complaints</a>
      <a href="/logout">Logout</a>
    </nav>
    <c:if test="${not empty error}"><p class="error">${error}</p></c:if>
    <c:if test="${not empty message}"><p class="message">${message}</p></c:if>

    <form action="/rooms/add" method="post">
      <input type="text" name="roomNo" placeholder="Room No" required />
      <input type="number" name="capacity" placeholder="Capacity" required />
      <button type="submit">Add Room</button>
    </form>

    <h3>Allocate / Deallocate</h3>
    <form action="/rooms/allocate" method="post">
      <input type="text" name="roomNo" placeholder="Room No" required />
      <input type="number" name="studentId" placeholder="Student ID" required />
      <button type="submit">Allocate</button>
    </form>

    <form action="/rooms/deallocate" method="post">
      <input type="text" name="roomNo" placeholder="Room No" required />
      <input type="number" name="studentId" placeholder="Student ID" required />
      <button type="submit">Deallocate</button>
    </form>

    <table>
      <tr><th>Room</th><th>Capacity</th><th>Allocated Students</th><th>Action</th></tr>
      <c:forEach var="r" items="${rooms}">
        <tr>
          <td>${r.roomNo}</td>
          <td><span class="badge">${r.studentIds.size()} / ${r.capacity}</span></td>
          <td>${r.studentIds}</td>
          <td>
            <form action="/rooms/delete/${r.roomNo}" method="post" style="display:inline">
              <button type="submit">Delete</button>
            </form>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div></body></html>
