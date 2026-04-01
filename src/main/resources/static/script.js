let incomeChart;
let expenseChart;
function loadEntry() {
    fetch("/finance/all")
        .then(res => res.json())
        .then(data => {
            console.log(data)
            const income = document.getElementById("income-list");
            const expense = document.getElementById("expense-list");

            income.innerHTML = "";
            expense.innerHTML = "";

            let incomeArray = [];
            let expenseArray = [];

            data.forEach(entry => {
                console.log(entry)
                let info = {
                    title: entry.title,
                    total: entry.amount * Math.floor(entry.durationDays / entry.intervalDays)
                };
                if (entry.type === "income") {
                    incomeArray.push(info);
                    income.innerHTML += `
                    <div class="finance" id="income">
                        <div>${entry.title}</div>
                        <div class="param">
                            <div>Gain:</div>
                            <div>+${entry.amount}€</div>
                        </div>
                        <div class="param">
                            <div>Duration</div>
                            <div>${entry.durationDays}d</div>
                        </div>
                        <div class="param">
                            <div>Interval</div>
                            <div>${entry.intervalDays}d</div>
                        </div>
                        <div class="button" onClick="deleteEntry(${entry.id})">Kustuta</div>
                    </div>
                    `
                } else {
                    expenseArray.push(info);
                    expense.innerHTML += `
                    <div class="finance" id="expense">
                        <div>${entry.title}</div>
                        <div class="param">
                            <div>Gain:</div>
                            <div>-${entry.amount}€</div>
                        </div>
                        <div class="param">
                            <div>Duration</div>
                            <div>${entry.durationDays}d</div>
                        </div>
                        <div class="param">
                            <div>Interval</div>
                            <div>${entry.intervalDays}d</div>
                        </div>
                        <div class="button" onClick="deleteEntry(${entry.id})">Kustuta</div>
                    </div>
                    `
                }
            });

            if (incomeChart) incomeChart.destroy();
            if (expenseChart) expenseChart.destroy();

            incomeChart = new Chart(document.getElementById("incomeChart"), {
                type: "pie",
                data: {
                    labels: incomeArray.map(item => item.title),
                    datasets: [{
                        data: incomeArray.map(item => item.total)
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: { legend: { position: 'bottom' }, title: { display: true, text: 'Tulude suhe' } }
                }
            })

            expenseChart = new Chart(document.getElementById("expenseChart"), {
                type: "pie",
                data: {
                    labels: expenseArray.map(item => item.title),
                    datasets: [{
                        data: expenseArray.map(item => item.total)
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: { legend: { position: 'bottom' }, title: { display: true, text: 'Kulude suhe' } }
                }
            })
        })
}
function addEntry() {
    let title = document.getElementById("title").value;
    let amount = document.getElementById("amount").value;
    let duration = document.getElementById("duration").value;
    let interval = document.getElementById("interval").value;
    let type = document.getElementById("type").value;
    if (title === undefined || title === null || title.trim() === "") {
        alert("Please, enter Entry's name")
    } else if (isNaN(amount) || amount === null || amount === undefined) {
        alert("Amount must be a number")
        return;
    } else if (isNaN(duration) || duration <= 0) {
        alert("duration must be a number and bigger than 0")
        return;
    } else if (isNaN(interval) || interval <= 0) {
        alert("interval must be a number and bigger than 0 and not bigger than duration")
        return;
    }

    let unit = {
        title: title,
        amount: amount,
        type: type,
        durationDays: duration,
        intervalDays: interval
    }

    fetch("/finance/", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(unit)
    }).then(() => loadEntry())
}
function deleteEntry(id) {
    fetch("/finance/" + id, {
        method: "DELETE"
    }).then(() => loadEntry())
}
loadEntry();