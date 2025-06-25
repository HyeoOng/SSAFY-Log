$(document).on("click", "#boardWriteLink", function(){
	const classList = document.querySelectorAll("#collapseTwo .collapse-item");
	for(item of classList){
		item.classList.remove("active");
	}
	$("#boardWriteLink").addClass("active");

    let msg = `<div class="container">
		<h2>의사등록</h2>
			<div class="mb-3">
				<label for="doctorId" class="form-label">의사 ID</label>
				<input type="number" class="form-control" id="doctorId" >
			</div>
			<div class="mb-3">
				<label for="name" class="form-label">이름</label>
				<input type="text" class="form-control" id="name" >
			</div>
			<div class="mb-3">
				<label for="age" class="form-label">나이</label>
				<input type="number" class="form-control" id="age" >
			</div>
			<div class="mb-3">
				<label for="experienceYears" class="form-label">경력</label>
				<input type="number" class="form-control" id="experienceYears" >
			</div>
			<div class="mb-3">
				<label for="specialtyCode" class="form-label">전공</label>
				<select id="specialtyCode" class="form-control">
					<option value="1" class="form-control">심장병학</option>
					<option value="2" class="form-control">피부과</option>
					<option value="3" class="form-control">신경학</option>
					<option value="4" class="form-control">소아과</option>
					<option value="5" class="form-control">정형외과</option>
					<option value="6" class="form-control">방사선학</option>
					<option value="7" class="form-control">정신과</option>
					<option value="8" class="form-control">소화기내과</option>
					<option value="9" class="form-control">종양학</option>
					<option value="10" class="form-control">응급의학</option>
				</select>
			</div>
			<button class="btn btn-primary" id="boardWriteBtn">등록</button>
	</div>`;
    $("#mainDiv").html(msg);
});
$(document).on("click","#boardWriteBtn",async function(){
	const doctorId = $("#doctorId").val();
	const name = $("#name").val();
	const age = $("#age").val();
	const specialtyCode = $("#specialtyCode").val();
	const experienceYears = $("#experienceYears").val();

	let data = JSON.stringify({ doctorId, name, age, specialtyCode, experienceYears });
	console.log(data);
	
	data = await fetch("/api/doctor", {
		method: "POST",
		headers,
		body: data
	});
	data = await data;
	if (data.ok) {
		$("#boardListLink").click();
	} else {
		alert("의사 등록 실패")
	}
});

$(document).on("click","#boardListLink",boardList);
async function boardList(){
	let data = await fetch("/api/doctor", {method: "GET"});	
	data = await data;
	if(data.status==204){
		alert("등록된 의사가 없습니다.");
		$("#boardWriteLink").click();
		return;
	}
	data = await data.json(); 
	const classList = document.querySelectorAll("#collapseTwo .collapse-item");
	for(item of classList){
		item.classList.remove("active");
	}
	$("#boardListLink").addClass("active");
	
	let htmlText = `
	<div class="container-fluid">
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">글목록</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table text-center table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>의사 ID</th>
                                            <th>이름</th>
                                            <th>나이</th>
											<th>경력</th>
											<th>전공</th>
                                        </tr>
                                    </thead>
                                    <tbody>`;
	data.forEach(doctor => {
		htmlText+=`
		<tr>
        	<td>${doctor.doctorId}</td>
        	<td><a onclick="doctorDetail(${doctor.doctorId})" style="cursor: pointer;">${doctor.name}</a></td>
        	<td>${doctor.age}</td>
			<td>${doctor.experienceYears}</td>
			<td>${doctor.specialtyName}</td>
		</tr>	
		`;
	});
	htmlText+= `</tbody></table></div></div></div></div>`;
	$("#mainDiv").html(htmlText);
};

async function doctorDetail(code){
	data = await fetch(`/api/doctor/${code}`, {method: "GET"});
	if(!data.ok){
		alert("의사 정보 조회 실패")
		return;
	}
	const doctor = await data.json();
	let msg = `<div class="container" id="updateForm">
		<h2>의사 정보</h2>
			<div class="mb-3">
				<label for="doctorId" class="form-label">의사 ID</label>
				<input type="number" class="form-control" id="doctorId" value="${doctor.doctorId}" readonly>
			</div>
			<div class="mb-3">
				<label for="name" class="form-label">이름</label>
				<input type="text" class="form-control modifyCol" id="name" value="${doctor.name}" readonly>
			</div>
			<div class="mb-3">
				<label for="age" class="form-label">나이</label>
				<input type="number" class="form-control modifyCol" id="age" value="${doctor.age}" readonly>
			</div>
			<div class="mb-3">
				<label for="experienceYears" class="form-label">경력</label>
				<input type="number" class="form-control modifyCol" id="experienceYears" value="${doctor.experienceYears}" readonly>
			</div>
			<div class="mb-3" id="specialtyDiv">
				<label for="specialtyCode" class="form-label">전공</label>
				<input type="text" class="form-control modifyCol" id="specialtyCode" value="${doctor.specialtyCode}" readonly>
			</div>
			<button class="btn btn-primary" id="doctorUpdateBtn">수정</button>
			<button class="btn btn-primary" id="doctorDeleteBtn">삭제</button>
	</div>`;
    $("#mainDiv").html(msg);
};

$(document).on("click", "#specialtyWrite", function(){
	const classList = document.querySelectorAll("#collapseTwo .collapse-item");
	for(item of classList){
		item.classList.remove("active");
	}
	$("#boardWriteLink").addClass("active");

    let msg = `<div class="container">
		<h2>전공등록</h2>
			<div class="mb-3">
				<label for="specialtyCode" class="form-label">전공 CODE</label>
				<input type="number" class="form-control" id="specialtyCode" >
			</div>
			<div class="mb-3">
				<label for="specialtyName" class="form-label">전공 이름</label>
				<input type="text" class="form-control" id="specialtyName" >
			</div>
			<button class="btn btn-primary" id="specialtyWriteBtn">등록</button>
	</div>`;
    $("#mainDiv").html(msg);
});

$(document).on("click","#specialtyWriteBtn",async function(){
	const specialtyCode = $("#specialtyCode").val();
	const specialtyName = $("#specialtyName").val();

	let data = JSON.stringify({ specialtyCode, specialtyName});
	console.log(data);
	
	data = await fetch("/api/specialty", {
		method: "POST",
		headers,
		body: data
	});
	data = await data;
	console.log(data.ok);
	// console.log(data);
});

$(document).on("click","#doctorUpdateBtn",function(){
	const list = document.getElementsByClassName("modifyCol");
	for (let item of list) {
		item.removeAttribute("readonly");
	}
	let text = `<label for="specialtyCode" class="form-label">전공</label>
				<select id="specialtyCode" class="form-control">
					<option value="1" class="form-control">심장병학</option>
					<option value="2" class="form-control">피부과</option>
					<option value="3" class="form-control">신경학</option>
					<option value="4" class="form-control">소아과</option>
					<option value="5" class="form-control">정형외과</option>
					<option value="6" class="form-control">방사선학</option>
					<option value="7" class="form-control">정신과</option>
					<option value="8" class="form-control">소화기내과</option>
					<option value="9" class="form-control">종양학</option>
					<option value="10" class="form-control">응급의학</option>
				</select>`
	$("#specialtyDiv").html(text);
	document.getElementById("doctorDeleteBtn").remove();
	document.getElementById("doctorUpdateBtn").remove();
	document.getElementById("updateForm").innerHTML += `<button class="btn btn-primary" id="doctorUpdateOkBtn">수정완료</button>`;
});

$(document).on("click","#doctorUpdateOkBtn",async function(){
	const doctorId = $("#doctorId").val();
	const name = $("#name").val();
	const age = $("#age").val();
	const specialtyCode = $("#specialtyCode").val();
	const experienceYears = $("#experienceYears").val();

	let data = JSON.stringify({ doctorId, name, age, specialtyCode, experienceYears });
	console.log(data);
	
	data = await fetch(`/api/doctor/${doctorId}`, {
		method: "PUT",
		headers,
		body: data
	});
	data = await data;
	if (data.ok) {
		alert("의사 수정 성공")
		doctorDetail(doctorId);
	} else {
		alert("의사 수정 실패")
	}
});

$(document).on("click","#doctorDeleteBtn",async function(){
	const doctorId = $("#doctorId").val();

	let data = await fetch(`/api/doctor/${doctorId}`, {method: "Delete"});
	data = await data;
	if (data.ok) {
		alert("의사 삭제 성공")
		$("#boardListLink").click();
	} else {
		alert("의사 삭제 실패")
	}
});

