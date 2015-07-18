<html>
<head>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>

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
			// - getJSON
			$.getJSON(btn.attr("url"), function(result){
				$("#message").text('');

				$.each(result, function(i, field){
					$("#message").append('['+field['id'] + "-" + field['message']+']');
				});
				
				$("#message").addClass("message");	
			});
		}
	});
});
</script>

<script>
$(function(){
	$("#add").click(function(){
		var addid = $("#addid").val()||'-1';
		var addmsg = $("#addmessage").val()||'NULL';

		if(debug)
			alert('object to be added = ['+addid+'/'+message+']');
		
		var $btn = $("#messagestable button");
		$.ajax({
			type: 'POST',
			url: $btn.attr("url"),
			data: {
				id: addid,
				message: addmsg
			},
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
	
});
</script>

<style>
div {
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

<div id="parea"><b>Practice Area<b>
<hr />
<input id="debug" type="checkbox" value="true">Debug</input>
<button url="webapi/myresource">myresource</button>
<button url="webapi/messages">messages</button>
<button dataType="xml" url="webapi/messages/xml">Xml Message</button>
<button id="button4" dataType="json" url="webapi/messages/json">Json
Message</button>
<p>Message from Ajax: <span id="message">None</span></p>
<br /></div>

<table id="messagestable" border="1" width="200">
	<caption>List of Messages</caption>
	<tbody>
		<tr>
			<th>Id</th>
			<th>Message</th>
		</tr>
	</tbody>

	<tfoot>
		<tr>
			<td><input id="addid" type="text" /></td>
			<td><input id="addmessage" type="text" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<button id="add" dataType="json" url="webapi/messages/json/add">Add</button>
			<button id="get" dataType="json" url="webapi/messages/json">Get</button>
			</td>
		</tr>
	</tfoot>
</table>


<hr />
<p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
for more information on Jersey!
</body>
</html>
