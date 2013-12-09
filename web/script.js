window.addEventListener("load", function() {
    var mainform = document.getElementById("mainform");
    var result = document.getElementById("result");
    
    mainform.onsubmit = function() {
        var tweet = document.getElementsByName("query")[0];
        var positif = document.getElementsByName("positif")[0];
        var negatif = document.getElementsByName("negatif")[0];
        var xhr = XMLHttpRequest();
        xhr.open("POST", "MainServlet", true);
        var postParams = "tweet=" + tweet.value + "&positif=" + positif.value + "&negatif=" + negatif.value;
        xhr.send(postParams);
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var response = JSON.parse(xhr.responseText);
                if (response['status'] == "success") {
                    result.innerHTML = "success";
                }
            }
        }
    }
});