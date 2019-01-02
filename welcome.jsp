<!-- Kima Brown-->
<!-- Java OO - Project 3-->
<!-- Page one - titled as such pageone.html-->
<!-- Page two - welcome.jsp-->
<!-- Page three - userinput.jsp-->
<!-- ServerRunner.java - code-->
<!-- Two car images included and a colored page on page two.-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kima Blue Book Choices</title>
</head>
<body bgcolor="grey">
<form action="http://localhost:8080/Car_Predictor_Server/ServerRunner" method="get">
<% String userlabel=(String)request.getAttribute("servletResponse"); %>
<%=userlabel %> <br> <br><br>
Input Safety: 
 	<select name="Safety"> 
 	  <option value="high">High</option> 
 	  <option value="med">Medium</option> 
 	  <option value="low">Low</option> 
 	</select> 
 	<br> <br>
Input Cost: 
 	<select name="Cost"> 
 	  <option value="vhigh">VHigh</option>
 	  <option value="high">High</option> 
 	  <option value="med">Medium</option> 
 	  <option value="low">Low</option> 
 	</select> 
 	<br><br>
Input Number of Doors: 
 	<select name="Doors"> 
 	  <option value="2">Two</option>
 	  <option value="3">Three</option> 
 	  <option value="4">Four</option> 
 	  <option value="5">FivePlus</option> 
 	</select> 
 	<br><br>
Input Trunk Space: 
 	<select name="TrunkSpace"> 
 	  <option value="small">Small</option>
 	  <option value="med">Medium</option> 
 	  <option value="big">Big</option> 
 	 </select> 
 	<br><br>
 Input Number of Person: 
 	<select name="Persons"> 
 	  <option value="2">Two</option>
 	  <option value="4">Four</option> 
 	  <option value="5">More</option> 
 	</select> 
 	<br><br>    
Input Maintenance: 
 	<select name="Maintenance"> 
 	  <option value="vhigh">VHigh</option>
 	  <option value="high">High</option> 
 	  <option value="medium">Medium</option> 
 	  <option value="low">Low</option> 
 	</select> 
 	<br><br> 
<input type="submit" value="Enter" name="Submit">
</form>
</body>
</html>
