#!/bin/sh

tmp_dir="/home/maria_dev/oozie/tmp"
sleep 60
echo "oozie job -kill $1"
kill  $(<"$tmp_dir/oozie_script.pid")
rm $tmp_dir/oozie_script.pid
TIMER_PID=$(<"$tmp_dir/timer.pid")
rm $tmp_dir/timer.pid
kill $TIMER_PID


return 0
