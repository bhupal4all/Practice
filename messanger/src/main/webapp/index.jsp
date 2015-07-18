<html>
<head>
<script
	src="${pageContext.request.contextPath}/js/jquery.js"></script>

<script>
$(function(){
	$("button").click(function(){
		var debug = $("#debug:checked").val() || false;

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
				var $table = $("#messagestable");
				$("#message").text('');

				$.each(result, function(i, field){
					$("#message").append('['+field['id'] + "-" + field['message']+']');
					
					$table.append('<tr><td>'+field['id']+'</td><td>'+field['message']+'</td></tr>');
				});
				
				$("#message").addClass("message");
				
				
			});
		}
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
	
	<div>
		<b>Practice Area<b>
		<hr/>
		<input id="debug" type="checkbox" value="true">Debug</input>
		<button url="webapi/myresource">myresource</button>
		<button url="webapi/messages">messages</button>
		<button dataType="xml" url="webapi/messages/xml">Xml Message</button>
		<button id="button4" dataType="json" url="webapi/messages/json">Json Message</button>
		<p>Message from Ajax: <span id="message">None</span></p>
		<br/>
		<table id="messagestable" border="1" width="200">
			<caption>List of Messages</caption>
			<tr>
				<th>Id</th>
				<th>Message</th>
			</tr>
		</table>
		
	</div>
	<hr/>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
</body>
</html>
