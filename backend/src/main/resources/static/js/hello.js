$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/api/users/0"
    }).then(function(data) {
        $('.user-id').append(data.id);
        $('.user-name').append(data.name);
        $('.user-email').append(data.email)
    });
});