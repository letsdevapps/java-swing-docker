# Java Swing Docker

## Docker

    docker build -t java-swing-docker .

    xhost +local:docker

    docker run -it --rm --env DISPLAY=$DISPLAY --volume /tmp/.X11-unix:/tmp/.X11-unix --name java-swing-docker java-swing-docker

    xhost -local:docker
