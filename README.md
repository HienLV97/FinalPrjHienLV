- Login Admin Page: check 3 cases:  
  - invaid email
  - invaid password
  - login success
- Add new Customer
- Manage Project (add, edit, delete, search table)
- Add Task (chọn lại Project đã add trên),
- Run full flow from: Login Admin Page > Add Customer > Add Project > Add Task



- Logs, Reports, Video record, Screenshot case fail (Allure or Extent)
- Parallel Execution cho toàn bộ project
- Các thông số configs ở file Properties
- Các data test ở file Excel (chia theo Sheet và File)

- Run allure report: allure serve target/allure-results
- allure generate target/allure-results -o target/allure-report --clean
