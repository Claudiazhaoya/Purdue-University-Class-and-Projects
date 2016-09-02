<?PHP
session_start();
?>

<html>
<head>
<title>CS34800 Exercises</title>
</head>


<?PHP

if (isset($_POST['Submit1'])) {

	$selected_radio = $_POST['response'];
	
		if ($selected_radio == 'a') {
			$a_status = 'checked';
			$_SESSION["Answer"] = "a";

		}
		else if ($selected_radio == 'b') {
			$b_status = 'checked';
			$_SESSION["Answer"] = "b";
		}
		
		else if ($selected_radio == 'c') {
			$c_status = 'checked';
			$_SESSION["Answer"] = "c";
		}
		
		else if ($selected_radio == 'd') {
			$d_status = 'checked';
			$_SESSION["Answer"] = "d";
		}
		
		else if ($selected_radio == 'e') {
			$e_status = 'checked';
			$_SESSION["Answer"] = "e";
		}
}

$QNumber = $_SESSION["Question"];

$Answer = $_SESSION["Answer"];
$Correct = $_SESSION["Correct"];


$_SESSION["Question"] = $QNumber + 1;

if ($QNumber == 7) {
	$NextAction = "theEnd.php";
}
else {
	$NextAction = "nextQuestion.php";
}

?>

<body>

<center>

<!--<img src="Q1.png" alt="Q1">-->

<?php 

if ($Answer == $Correct) {
	echo "Congrats!";
}
else {
	echo "Sorry :(";
}
?>

<FORM NAME ="form1" METHOD ="POST" ACTION =<?PHP print $NextAction; ?>>

<P>
<INPUT TYPE = "Submit" Name = "NextQuestion"  VALUE = "Go to next question">
</FORM>

</center>

</body>
</html>


