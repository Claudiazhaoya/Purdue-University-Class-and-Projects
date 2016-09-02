<?PHP
session_start();
$_SESSION['username'];
$_SESSION['password'];
$_SESSION['firstname'];
$_SESSION['lastname'];
?>
<html>
<head>
<title>LOGIN/Register</title>
</head>

<body>
<br>
<?PHP
if($_SESSION['firstname']!=""){
	echo "Hi ",$_SESSION['firstname'],"  ",$_SESSION['lastname'],"  ";
	echo ('<a href="Logout.php">Logout</a>');
}
?>
<br>
<form name="login" method="POST" action="process.php">
<p style=" text-indent:5em;">Login</style><br>
Username:<br><input type="text" name="username"><br>
Password:<br><input type="password" name="password"><br>
<input type="submit" name='Login' value='Login'>
</form>
<br>
<br>
<br>
<br>
<form name="register" method="POST" action="process.php">
<p style=" text-indent:5em;">Register</style><br>
Username:<br><input type='text' name="username"><br>
First Name:<br><input type='text' name="firstname"><br>
Last Name:<br><input type='text' name="lastname"><br>
Password:<br><input type='password' name="password"><br>
<input type="submit"name= 'Register' value='Register'>
</form>
<a href="Movie.php">Movie</a>
<br>
<a href="Actors.php">Actor</a>
</body>
</html>
