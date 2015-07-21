<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.3.1.1.css">
    <!-- DataTables CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css">

    <!-- DataTables Responsive CSS -->
    <link href="${pageContext.request.contextPath}/css/dataTables.responsive.css" rel="stylesheet">

<style>
div .practice {
	width: 25%;
	text-align: center;
	font-size: 14px;
	border: 1px solid black;
}

.message {
	color: green;
}
</style>
</head>

<body>
<h2>Jersey RESTful Web Application!</h2>
<p><a href="webapi/myresource">Jersey resource</a>

<div class="practice" id="parea"><b>Practice Area<b>
<hr />
<input id="debug" type="checkbox" value="true">Debug</input>
<button url="webapi/myresource">myresource</button>
<button url="webapi/messages">messages</button>
<button dataType="xml" url="webapi/messages/xml">Xml Message</button>
<button id="button4" dataType="json" url="webapi/messages/json">Json
Message</button>
<p>Message from Ajax: <span id="message">None</span></p>
<br /></div>

<div class="panel panel-default">
	<div class="panel-heading">
		List of Messages
	</div>
	<!-- /.panel-heading -->
	<div class="panel-body">
		<div class="dataTable_wrapper">
			<table id="messagestable" class="table table-striped table-bordered" cellspacing="0" width="30%">
				<thead>
					<tr>
						<th>Id</th>
						<th>Message</th>
					</tr>
				</thead>

				<tbody>
				</tbody>

				<tfoot>
					<tr>
						<td><input id="addid" type="text" /></td>
						<td><input id="addmessage" type="text" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<button id="add" class="btn btn-primary" dataType="json" url="webapi/messages/json/add">Add</button>
						<button id="get" class="btn btn-warning" dataType="json" url="webapi/messages/json">Get</button>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>		

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Message Information</h4>
      </div>
      <div class="modal-body">
		<p>
			<table class="table table-bordered">
				<tr><td>Id : </td><td><span id="modelid"></span></td></tr>
				<tr><td>Message : </td><td><span id="modelmsg"></span></td></tr>
			</table>
		</p>
		<p>Message from Ajax: <button id="modelbtn" class="btn btn-default" dataType="json" url="webapi/messages/json">Json Message</button><span id="modelmessage"></span></p>
	  </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<hr />
<p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
for more information on Jersey!
</body>
</html>

<script src="${pageContext.request.contextPath}/js/jquery-2.0.2.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>

<script>
$(function(){
	var debug = $("#debug:checked").val() || false;
	
	$("#parea button").click(function(){

		var btn = $( this );

		if (btn.attr("dataType") == undefined)
		{
			// ajax
			$.ajax({
			  url: btn.attr("url"),
			  beforeSend: function(){
				  $("#message").text('... Loading ...').removeClass("message").css("background","none");
				  if (debug)
					alert('requesting for \''+btn.attr("url")+'\'');
			  },
			  error: function(){
				  $("#message").text('-ERROR-').removeClass("message").css("background","red").css("color","white");
			  }
			}).done(function(data) {
				if (debug)
					alert('response: '+data);
			  $("#message").text(data).addClass("message");
			});
		}		
		else if (btn.attr("dataType") == 'xml')
		{
			// ajax
			$.ajax({
			  url: btn.attr("url"),
			  dataType: "xml",
			  beforeSend: function(){
				  $("#message").text('... Loading ...').removeClass("message").css("background","none");
				  if (debug)
					alert('requesting for \''+btn.attr("url")+'\'');
			  },
			  error: function(){
				  $("#message").text('-ERROR-').removeClass("message").css("background","red").css("color","white");
			  }
			}).done(function(xml) {
				if (debug)
					alert('response: '+xml);
				
				$("#message").text('').addClass("message");;
				$(xml).find('message').each(function(){
					if ($(this) != '')
						$("#message").append('['+$(this).find('id').text()+','+$(this).find('message').text()+']');
				});
			});
		}
		else
		{
			$("#message").text('... Loading ...').removeClass("message").css("background","none");

			// - getJSON
			$.getJSON(btn.attr("url"), function(result){
				$("#message").text(' ');

				$.each(result, function(i, field){
					$("#message").append('['+field['id'] + "-" + field['message']+']');
				});
				
				$("#message").addClass("message");	
			});
		}
	});
});

$(function(){	
	$("#add").click(function(){
		var addid = $("#addid").val()||'-1';
		var addmsg = $("#addmessage").val()||'NULL';

		var jsonData = "{\"id\": \""+addid+"\", \"message\": \""+addmsg+"\"}";
		
		alert(jsonData);
		
		var $btn = $("#messagestable button");
		$.ajax({
			type: 'POST',
			url: $btn.attr("url"),
			dataType: 'json',
			contentType: 'application/json',
			data: jsonData,
			error: function(){
				alert('error');
			},
			success: function(){
				if(debug)
					alert('success');
				
				$("#addid").val('');
				$("#addmessage").val('');
				$("#messagestable tbody").append('<tr><td>'+addid+'</td><td>'+addmsg+'</td></tr>');
			}
		});
	});

	$("#get").click(function(){
		var btn = $("#get");

		alert(btn.attr("url"));
		$.getJSON(btn.attr("url"), function(result){
			$.each(result, function(i, field){
				$("#messagestable tbody").append('<tr><td>'+field['id']+'</td><td>'+field['message']+'</td></tr>');
			});
		});
	});
	
	$("#messagestable").on('click','tbody tr',function(){
		var $self = $( this );
		var id = $self.closest('tr').children().first().text();	
		var message = $self.closest('tr').children().next().text();	
		
		$("#modelid").text(id);
		$("#modelmsg").text(message);
		$("#myModal").modal('show');
	});

	$("#modelbtn").on('click',function(){
		var btn = $( this );
		$("#modelbtn").hide();
		
		$("#modelmessage").text('... Loading ...').removeClass("message").css("background","none");
		
		// - getJSON
		$.getJSON(btn.attr("url"), function(result){
			$("#modelmessage").text(' ');

			$.each(result, function(i, field){
				$("#modelmessage").append('['+field['id'] + "-" + field['message']+']');
			});
			
			$("#modelmessage").addClass("message");	
		});		
	});

	$("div.modal-footer").on('click', function(){
		$("#modelmessage").text('');
		$("#modelbtn").show();
	});
});
</script>
