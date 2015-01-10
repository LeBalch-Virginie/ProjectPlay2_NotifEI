jQuery(function($) {
    $("#medicament-search").keyup(function() {
        if ($(this).val() != "") {
            $.getJSON(
                "autocomplete/medicament/" + $(this).val(),
                function(data) {
                    $("#medicament-search").autocomplete({
                        source: data
                    });
                }
            );
        }
    });

    $("#dispositif-form").submit(function(e) {
        e.preventDefault();
        $.post(
            "/recherche/dispositif",
            { "dispositif-search" : $("#dispositif-search").val() },
            function(data) {
               $("#dispositif-effets").empty();
                for (var i = 0; i < data.length; i++) {
                    $("#dispositif-effets").append("<li>" + data[i] + "</li>");
                }
            },
            "json"
        );
        return false;
    });

    $("#dispositif-search").keyup(function() {
        if ($(this).val() != "") {
            $.getJSON(
                "autocomplete/dispositif/" + $(this).val(),
                function(data) {
                    $("#dispositif-search").autocomplete({
                        source: data
                    });
                }
            );
        }
    });

    $("#cosmetique-search").keyup(function() {
        if ($(this).val() != "") {
            $.getJSON(
                "autocomplete/cosmetique/" + $(this).val(),
                function(data) {
                    $("#cosmetique-search").autocomplete({
                        source: data
                    });
                }
            );
        }
    });
});