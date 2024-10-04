const loginBtn = document.getElementById("loginBtn"); 
loginBtn.addEventListener("click", async function(){
    
	const id = document.getElementById("id").value;
	    const pw = document.getElementById("pw").value;

	    let data = {id, pw, sign:"login"};
	    data = JSON.stringify(data);
		
	    let response = await fetch('main', {method:"post", body:data}); 
	    data = await response.json();	
		
		if(data.name){
			window.location.href = `post.html?no=${encodeURIComponent(data.no)}&name=${encodeURIComponent(data.name)}`; 
		} else {
			alert(data.msg);
		}
	
});