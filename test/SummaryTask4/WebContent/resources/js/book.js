function changeBookName(bookId) {
    var newName = $("#change-book-name").val();
    if (newName) {
            $.ajax({
                url: "Controller",
                cache: false,
                type: 'POST',
                data: {
                    command: "editBook",
                    bookId: bookId,
                    field: "name",
                    name: newName
                },
                success: function () {
                    $("span#bookName").text(newName);
                    $('#change-name-modal').modal('hide');
                    document.getElementById("change-name-form").reset();
                }
            });
        }
    }
    function changeBookAuthor(bookId) {
    var newAuthor = $("#change-author-name").val();
        if (newAuthor) {
    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "editBook",
            bookId: bookId,
            field: "author",
            author: newAuthor
        },
        success: function () {
            $("span#bookAuthor").text(newAuthor);
            $('#change-author-modal').modal('hide');
            document.getElementById("change-author-form").reset();
        }
    });}
}

function changeBookPublisher(bookId) {
    var newPublisher = $("#change-publisher-name").val();
    if (newPublisher) {
    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "editBook",
            bookId: bookId,
            field: "publisher",
            publisher: newPublisher
        },
        success: function () {
            $("span#bookPublisher").text(newPublisher);
            $('#change-publisher-modal').modal('hide');
            document.getElementById("change-publisher-form").reset();
        }
    });}
}

function validateBookPublicationDate(bookId) {
    resetError("#edit-publicationDate-error");
    if (new Date($("#change-publicationDate").val()) > Date.now()) {
        $("#edit-publicationDate-error").text("Date field can't be earlier than today");
    } else {
        changeBookPublicationDate(bookId);
    }
}

function changeBookPublicationDate(bookId) {
    var newPublicationDate = $("#change-publicationDate").val();

    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "editBook",
            bookId: bookId,
            field: "publicationDate",
            publicationDate: newPublicationDate
        },
        success: function () {
            $("span#bookPublicationDate").text(newPublicationDate);
            $('#change-publicationDate-modal').modal('hide');
            document.getElementById("change-publicationDate-form").reset();
        }
    });
}
function validateBookAvailable(bookId) {
    resetError("#edit-available-error");
    var available = $("#change-available").val();
    if (!available) {
        $("#edit-available-error").text("Available field can't be empty");
    } else if (available < 0) {
        $("#edit-available-error").text("Available field can't be negative");
    } else {
        changeBookAvailable(bookId);
    }
}

function changeBookAvailable(bookId) {
    var newAvailable = $("#change-available").val();

    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "editBook",
            bookId: bookId,
            field: "available",
            available: newAvailable
        },
        success: function () {
            $("span#bookAvailable").text(newAvailable);
            $('#change-available-modal').modal('hide');
            document.getElementById("change-available-form").reset();
        }
    });
}

function deleteBook(bookId) {
    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "editBook",
            bookId: bookId,
            field: "delete"
        },
        success: function () {
            window.location.href = "Controller?command=showAllBooks";
        }
    });
}

function validateBookForm() {
    resetError("#publicationDate-error");
    resetError("#available-error");

    var elems = document.forms.addBook.elements;

    if (new Date(elems.InputBookPublicationDate.value) > Date.now()) {
        $("#publicationDate-error").text("Date field can't be earlier than today");
    } else if (elems.InputBookAvailable.value < 0) {
        $("#available-error").text("Available field can't be negative");
    } else {
        addBook(elems);
    }
}

function addBook(elems) {
    $("#addBook-error").text("");
    if (!elems.InputBookName.value ||
        !elems.InputBookAuthor.value ||
        !elems.InputBookPublisher.value ||
        !elems.InputBookPublicationDate.value ||
        !elems.InputBookAvailable.value) {
        $("#addBook-error").text("Fields can't be empty");
    } else {

        $.ajax({
            url: "Controller",
            cache: false,
            type: 'POST',
            data: {
                command: "addBook",
                name: elems.InputBookName.value,
                author: elems.InputBookAuthor.value,
                publisher: elems.InputBookPublisher.value,
                publicationDate: elems.InputBookPublicationDate.value,
                available: elems.InputBookAvailable.value
            },
            statusCode: {
                200: function () {
                    window.location.href = "Controller?command=showAllBooks";
                },
                423: function () {
                    $("#addBook-error").text("Such book already exists");
                },
                500: function () {
                    $("#addBook-error").text("Oops some errors occurred on server");
                }
            }
        });
    }
}