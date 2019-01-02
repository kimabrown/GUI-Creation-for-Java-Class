<!-- Kima Brown-->
<!-- Java OO - Project 3-->
<!-- Page one - titled as such pageone.html-->
<!-- Page two - welcome.jsp-->
<!-- Page three - userinput.jsp-->
<!-- ServerRunner.java - code-->
<!-- Two car images included and a colored page on page two.-->
<HTML>
<HEAD> <TITLE> Kima's Blue Book Prediction </TITLE> </HEAD>
<BODY>
<img src="teslaSUVmodelX.jpg" alt="Trulli" width="500" height="333">
<form action="http://localhost:8080/Car_Predictor_Server/ServerRunner" method="get">
<%String selectionText=(String)request.getAttribute("carlist"); %>
Your Prediction is:<br><%=selectionText %><br>

<input type="submit" value="$120,000" name="Submit">
</form>

</BODY>
</HTML>