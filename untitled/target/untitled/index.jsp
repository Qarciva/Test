<!DOCTYPE html>
<html>
<body>
    <h2>Hello World!</h2>
    <input type="button" value="Click Me" onclick="callJavaMethod()" />
    <input type text id="result" />
    

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
