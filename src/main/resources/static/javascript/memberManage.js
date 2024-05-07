fetch("localhost:8080/thanhvien/getAll", {
    method: "GET",
    headers: {"ContentType": "application/json"}
}).then(
    response => JSON.parse(response)
).then(data => {
    data.array.forEach(mem => {
        document.getElementById("membersContainer").innerHTML+=`<div>${mem['MaTV']} ${mem['HoTen']} ${mem['Khoa']} ${mem['Nganh']} ${mem['SDT']}</div>`;
    });
}).catch(error => {
    alert(error)
})