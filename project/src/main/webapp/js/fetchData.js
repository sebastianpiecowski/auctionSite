const categoryURL = 'http://localhost:8080/announcement/category_id=';
const $list = $('#announcement');
const baseURL = 'http://localhost:8080/';
function getByCategory(id) {
	var test=document.getElementById("announcement");
	$.ajax({
		url : categoryURL + id
	}).done(ret => {
		ret.forEach(announcement => {
			var startDate=$.datepicker.formatDate('yy-mm-dd', new Date(announcement.startDate));
			var endDate=$.datepicker.formatDate('yy-mm-dd', new Date(announcement.endDate));
			const $element =$(`
			<div class="col-md-4 py-2" id="announcement_element">
			  <div class="card">
				<img class="card-img-top" src="logo3.png" alt="Card image cap">
				<div class="card-body">
				  <h5 class="card-title">${announcement.title}</h5>
				  <p class="card-text">Price :`+announcement.price+`</p>
				  <p class="card-text">Validity `+startDate+` to `+endDate+`</p>
				  <a href="#" class="btn btn-primary">More</a>
				</div>
			  </div>
			</div>
			`);
			test.append($element);
		});
	});
}
function getDataIndex() {
	
	$.ajax({
		url : baseURL+'announcement/all'
	}).done(ret => {
		ret.forEach(announcement => {
			var startDate=$.datepicker.formatDate('yy-mm-dd', new Date(announcement.startDate));
			var endDate=$.datepicker.formatDate('yy-mm-dd', new Date(announcement.endDate));
			const $element = $(`
			<div class="col-md-4 py-2" id="announcement_element">
			  <div class="card">
				<img class="card-img-top" src="logo3.png" alt="Card image cap">
				<div class="card-body">
				  <h5 class="card-title">${announcement.title}</h5>
				  <p class="card-text">Price :${announcement.price}</p>
				  <p class="card-text">Validity `+startDate+` to `+endDate+`</p>
				  <a href="#" class="btn btn-primary">More</a>
				</div>
			  </div>
			</div>
			`);
			$list.append($element);
		});
	});
}