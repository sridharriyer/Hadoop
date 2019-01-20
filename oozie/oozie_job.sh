#!/bin/sh

process_dir="/home/maria_dev/oozie/batchjobs/process"
completed_dir="/home/maria_dev/oozie/batchjobs/completed"
failed_dir="/home/maria_dev/oozie/batchjobs/failed"


while true
do
        echo 'Waiting for signal...'
        for signal_file in $(ls -tr $process_dir/ | head -1); do
			signal=$(basename "$signal_file")
			# write the logic here to check if the signal file is valid.
			# if not valid move file to invalid signal directory and break loop
                        echo $signal
			oozie_job_id=$(oozie job -oozie http://172.18.0.2:11000/oozie/ -DSignalName=$signal -config job.properties -run) 
			oozie_job_id=${oozie_job_id:5}
                        echo $oozie_job_id
			sleep 10s
			flag=true
			while $flag
			do
				job_status=$(oozie job -poll $oozie_job_id -interval 1 -oozie http://172.18.0.2:11000/oozie -verbose)
				case $job_status in
				RUNNING | SUBMITTED)
                                                # This block is never reached; but as a precaution have put this
						sleep 5s
                                                echo $job_status
                                                flag=true
						;;
				SUCCEEDED) 
						mv $process_dir/$signal_file $completed_dir/$signal_file
                                                echo $job_status
						flag=false
						;;
				KILLED | FAILED | SUSPENDED)
						mv $process_dir/$signal_file $failed_dir/$signal_file
						flag=false
						;;
				esac
			done
		done
done
