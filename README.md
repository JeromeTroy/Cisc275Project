# Cisc275Project
Group Project for CISC 275

Each of us will pull this from the repo, then add your name to this file.
After that push it back up to the repo.  Doing this ensures each of us can pull and push from github.

Names:
Jerome Troy,
Matt Collins,
Evie Boyd




MVC design pattern

Model

    main model
        main character - fish
            move
            action - eat/get stuck
        map
            main segment
            repeated segment
            local segment (around fish)
            start
            stop
            boundaries
        trash
            location
            motion
        food
            location
            motion
            effect
        time

    minigame model
        main character - scuba diver
            move
            action - grab/drop
        map
            one segment
            bounds
            goal location (trash can)
        trash
            location
            motion
        time
    
View
    
    map images
        main map
        repeats
        mini map
    
    trash images
        decider for which image is shown
    
    food images
        decider for which image is shown
    
    fish images
        travel - directions
        eating?
        get caught in trash
        intro
        game over
    
    diver images
        travel - directions
        grab
        drop
    
    Other screens
        title
        tutorials
        winning
        losing

Controller

    call minigame
        create tutorial for minigame
        show map w/ diver
        have diver accomplish goal
        timer
        exiting requirements
    
    return to game
        exit minigame
        return to fish's location
        remove trash
    
    input to model
    
    change view
    
    main
        call title
        call tutorial
        start main game
        watch for effects - display these
            when trashed - call minigame
            end mini game - return to main game, effects occur on surrounding trash
        timer out - call losing scene
        get to finish - call wining scene
        display score
        exit

    
