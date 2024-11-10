<!DOCTYPE html>
<html>
<body>

    <input type="text" id="email" name="email" placeholder="Email" >
    <input type="text" id="password" name="password" placeholder="Password" >
    <input type="button" value="Register" onclick="callJavaMethod()" />
    

    <script>
        function callJavaMethod() {
            fetch('execute')
                .then(response => response.text())
                .then(data => {
                    document.getElementById('result').value = data;
                })
                .catch(error => console.error('Error:', error));
        }
    </script>
</body>
</html>
