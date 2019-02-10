#!/bin/sh

tmp_dir="/home/maria_dev/oozie/tmp"
oozie_job_pid_file="oozie_script.pid"
timer_pid_file="timer.pid"

sleep 60
echo "oozie job -kill $1"
kill  $(<"$tmp_dir/oozie_script.pid")
rm $tmp_dir/oozie_script.pid
TIMER_PID=$(<"$tmp_dir/timer_pid_file")
rm $tmp_dir/timer_pid_file
kill $TIMER_PID


return 0
