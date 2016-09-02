<?PHP
session_start();
?>

<html>
<head>
<title>CS34800 Exercises</title>
</head>


<?PHP

$_SESSION["Question"] = 1;


    try {
        // open connection to MongoDB server
        $conn = new Mongo('localhost');
        
        // access database
        $db = $conn->test;
        
        // access collection
        $collection = $db->items;
        
        // formulate AND query
        $criteria = array('QNumber' => 1);
        
        // retrieve only 'name' and 'price' keys
        $fields = array('correct');
        
        // execute query
        $cursor = $collection->find($criteria, $fields);
        
        // iterate through the result set
        // print each document
        foreach ($cursor as $obj) {
            $correct = $obj['correct'];
        }
        
        // disconnect from server
        $conn->close();
    } catch (MongoConnectionException $e) {
        die('Error connecting to MongoDB server');
    } catch (MongoException $e) {
        die('Error: ' . $e->getMessage());
    }

$_SESSION["Correct"] = $correct;

?>


<body>

<br><br><br>

<center>

<img src="Q1.png" alt="Q1">

<br><br>

<FORM NAME ="form1" METHOD ="POST" ACTION ="result.php">

<INPUT TYPE = 'Radio' Name ='response'  value= 'a' <?PHP print $a_status; ?>>a

<INPUT TYPE = 'Radio' Name ='response'  value= 'b' <?PHP print $b_status; ?>>b

<INPUT TYPE = 'Radio' Name ='response'  value= 'c' <?PHP print $c_status; ?>>c

<INPUT TYPE = 'Radio' Name ='response'  value= 'd' <?PHP print $d_status; ?>>d

<INPUT TYPE = 'Radio' Name ='response'  value= 'e' <?PHP print $e_status; ?>>e

<P>
<INPUT TYPE = "Submit" Name = "Submit1"  VALUE = "Submit answer">
</FORM>

</center>

</body>
</html>


