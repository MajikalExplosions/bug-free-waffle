set -e
javac MyBot.java
./halite --replay-directory replays/ -vvv --width 32 --height 32 "java MyBot 104 176 334 160" "java MyBot 104 176 334 160"