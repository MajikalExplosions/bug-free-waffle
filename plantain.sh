set -e
javac MyBot.java
./halite --replay-directory replays/ -vvv --width 32 --height 32 "java MyBot 100 187 352 304" "java MyBot 100 187 352 304"