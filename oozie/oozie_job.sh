#!/bin/sh

process_dir="/home/maria_dev/oozie/batchjobs/process"
completed_dir="/home/maria_dev/oozie/batchjobs/completed"
failed_dir="/home/maria_dev/oozie/batchjobs/failed"
tmp_dir="/home/maria_dev/oozie/tmp"

if [ -e $tmp_dir/oozie_workflow.lck ]
then
    echo "A process is in progress. Issue ps -ux | grep oozie.workflow.sh  to find the process id"
    echo "If no process exists, delete the lck file to continue.."
    exit
else
    touch $tmp_dir/oozie_workflow.lck
fi

while true:
do
        for signal_file in $(ls -tr | head -1 $process_dir/*); do
			signal=$(basename "$signal_file")
			oozie_job_id=$(oozie job -oozie http://172.18.0.2:11000/oozie/ -DSignalName=$signal -config job.properties -run) 
			oozie_job_id=$("$oozie_job_id" | awk '{print $2}')
			if [$oozie_job_id -eq ''] then
			   rm $tmp_dir/oozie_workflow.lck
			   exit
			else    
			source /home/maria_dev/oozie/timer.sh $OOZIE_JOB_ID & echo $! > $tmp_dir/timer.pid &
		    fi
			flag=true
			while $flag:
			do
				status=$(oozie job -poll "$oozie_job_id" -oozie http://172.18.0.2:11000/oozie -verbose) 
				case $status in
				RUNNING | SUBMITTED)
						sleep 1m 
						;;
				SUCCEEDED) 
						mv $process_dir/$signal $completed_dir/$signal
						flag=false
						;;
				KILLED | FAILED | SUSPENDED)
						mv $process_dir/$signal $failed_dir/$signal
						flag=false
						;;
				esac
				kill $(<"$tmp_dir/timer.pid")
				rm $tmp_dir/timer.pid
			done
		done
done
