<%@page language="java" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    </body>
        <h2>Munaf Calculator</h2>

         <form action="resultCalc">
                <label for="num1">Enter 1st Number :</label>
                <input type="text" id="num1" name="num1"><br>
                <label for="num2">Enter 2nd Number :</label>
                <input type="text" id="num2" name="num2"><br>

                <label for="Char">Enter Symbol(+,-,*,/) :</label>
                <input type="text" id="Char" name="Char"><br>

                <input type="submit" value="Submit">
         </form>

    </body>
</html>