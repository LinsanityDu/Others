Parse a formula string (only contains “+-()”, no “*/“).
For example,
5 + 2x – ( 3y + 2x - ( 7 – 2x) – 9 ) = 3 + 4y
Parse this string, with a given float of ‘x’ value, output a float for ‘y’ value.

没有乘除法就没有优先级的问题了，直接一个pass把括号的符号展开就行。下面的代码没有考虑非法情况

double evaluate(const string& f, double x_val) {
    double sum_y_left = 0, sum_y_right = 0;
    double sum_num_left = 0, sum_num_right = 0;
    double *cur_sum_y = &sum_y_left, *cur_sum_num = &sum_num_left;
    int last_op = 1, bracket_op = 1;
    stack<int> brackets;
   
    for(int i = 0; i <= f.size(); ++i) {
        char c = f[i];
        if(f[i] >= '0' && f[i] <= '9') {
            int over = i + 1;
            while(f[over] >= '0' && f[over] <= '9') ++over;
            double number = atoi(f.substr(i, over-i).c_str()) * last_op * bracket_op;
           
            if(f[over] == 'x') {
                *cur_sum_num += number * x_val;
                i = over;
            } else if(f[over] == 'y') {
                *cur_sum_y += number;
                i = over;
            } else {
                *cur_sum_num += number;
                i = over - 1;
            }
        } else if( c == 'x' ) {
            *cur_sum_num += last_op * bracket_op * x_val;
        } else if( c == 'y' ){
            *cur_sum_y += last_op * bracket_op;
        } else if( c == '(' ) {
            brackets.push(last_op);
            bracket_op *= last_op;
            last_op = 1;
        } else if( c == ')' ) {
            bracket_op /= brackets.top();
            brackets.pop();
        } else if( c == '+' || c == '-' ) {
            last_op = c == '+' ? 1 : -1;
        } else if( c == '=' || c == '\0' ) {
            cur_sum_num = &sum_num_right;
            cur_sum_y = &sum_y_right;
            last_op = 1;
            brackets = stack<int>();
        }
    }
    return (sum_num_right - sum_num_left) / (sum_y_left - sum_y_right);
}
