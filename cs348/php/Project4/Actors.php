<?PHP
session_start();
?>

<html>
<head>
<title>Actors</title>
</head>
</body>
<br>
<?PHP
if($_SESSION['log']!=0){
        echo "Hi ",$_SESSION['firstname'],"  ",$_SESSION['lastname'],"  ";
        echo ('<a href="Logout.php">Logout</a>');
}
?>
<br>
<form name="actor" method="POST" action="ActorInfo.php">
<br>
Movie Title:<br><input type="text" name="actors" value="Name"><br>
<input type="submit" name='Actor'value="Submit"><br>
<a href="Actors.php">Actor</a><br>
<a href="Login.php">Login/Register</a>
</body>
</html>

~                        
