<?PHP
session_start();
$_SESSION['log'];
?>
<html>
<head>
<title> WELCOME</title>

<body>
<center>
<b>
<fontsize="15">Welcome to the CS34800 movie database!</fontsize>
</b>
<br>
<?PHP
if($_SESSION['log'] != 0){
	echo "Hi ",$_SESSION['firstname'],"  ",$_SESSION['lastname'],"  ";
	echo ('<a href="Logout.php">Logout</a>');
}
?>
<br><br>
</center>
<a href="Movie.php">Movies </a>
<br><br>
<a href="Actors.php">Actors</a>
<br><br>
<a href="Login.php">Login/Register</a>
</font>
<br>
<?PHP
?>
</body>
<html>
