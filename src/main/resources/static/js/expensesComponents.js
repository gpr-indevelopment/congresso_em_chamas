function renderExpenses(expenses, element) {
    element.innerHTML = `   <div class="chart-container mr-3 mb-3">
                                <canvas id="chart"></canvas>
                            </div>
                            <div class="border shadow d-flex justify-content-center align-items-center chart-details-container">
                                <small class="text-center w-50">Clique em uma despesa ou data para visualizar seus detalhes.</small>
                            </div>`;
    let expenseData = buildExpenseData(expenses);
    var ctx = document.getElementById("chart").getContext('2d');
    new Chart(ctx, {
        // The type of chart we want to create
        type: 'bar',
        // The data for our dataset
        data: {
            labels: expenseData.dates,
            datasets: [{
                fill: false,
                label: 'Despesas',
                backgroundColor: '#0078ff',
                borderColor: '#0078ff',
                data: expenseData.values,
                lineTension:0
            }]
        },

        // Configuration options go here
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales:{
                yAxes:[
                    {
                        ticks:{
                            callback: function(value, index, values){
                                return "R$ " + value;
                            }
                        }
                    }
                ]
            }
        }
    });
}

function buildExpenseData(expenses){
    let expenseData = {
        values: [],
        dates: []
    }
    expenses.sort((a, b) => a.month - b.month)
    expenses.forEach(expense => {
        let currentMonth = (expense.month < 10) ? "0"+expense.month : expense.month;
        expenseData.values.push(expense.value.toFixed(2));
        expenseData.dates.push(`${currentMonth}/${expense.year}`)
    });
    return expenseData;
}