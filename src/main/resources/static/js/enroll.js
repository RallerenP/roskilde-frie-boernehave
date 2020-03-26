$(document).ready(() => {
    $('#cancel').click(() => {
        window.location.replace("http://localhost:8080/")
    });

    $('#confirm').click(async () => {

        try {
            const child_name = $('#child_name').val();
            const child_birthday = $('#child_birthday').val();

            const child = {
                name: child_name,
                birthday: child_birthday
            }

            const parent_name = $('#parent1_name').val();
            const parent_phone = $('#parent1_phone').val();

            const parent = {
                name: parent_name,
                phone: parent_phone
            };

            console.log(child, parent);

            const response = await fetch('http://localhost:8080/children', {
                method: "post",
                body: JSON.stringify(child)
            });

            const child_response = await response.json();

            console.log(child_response);

            const newChild = child_response.body;

            const response2 = await fetch('http://localhost:8080/parents', {
                method: "post",
                body: JSON.stringify(parent)
            });

            const parent_response = await response2.json();

            const newParent = parent_response.body;

            const child_edit_response = await fetch(`http://localhost:8080/children/${newChild.id}`, {
                method: "put",
                body: JSON.stringify({
                    parentIds: [newParent.id]
                })
            });

            console.log(await child_edit_response.json());

            const parent_edit_response = await fetch(`http://localhost:8080/parents/${newParent.id}`, {
                method: "put",
                body: JSON.stringify({
                    childrenIds: [newChild.id]
                })
            });

            const parent_edit_response_object = await parent_edit_response.json();

            if (parent_edit_response_object.message === "Success") window.location.replace("http://localhost:8080/success")
            else window.location.replace("http://localhost:8080/error")
        } catch (e) {
            window.location.replace("http://localhost:8080/error")
        }
    });
});