# Cisc275Project
Group Project for CISC 275

Each of us will pull this from the repo, then add your name to this file.
After that push it back up to the repo.  Doing this ensures each of us can pull and push from github.

Names:
Jerome Troy,
Matt Collins,
<<HEAD
Evie Boyd,
Alani Johnson




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

    
=======
Evie Boyd,



Classes Needed:

Model

    tutorial model

    main model

        map

        unique part

        repeats

        timer

        local trash amounts

    trash

        locations

        motion

    food 

        locations

        motion

        effects

    mini model

        map

        timer

    fish character

        location

        actions

    diver character

        location

        actions
        
        
    

View

    title screen

    tutorial - main game

    tutorial - mini game

    main game

        main map

        local scene

    mini game

        map

        scene

    trash

    food

    fish character

        directions

        actions

            swim

            eat

            get stuck

    diver character

        directions

        actions

            swim

            grab

            drop

    losing scene

    winning scene

    


Controller

    main
        call title
        call tutorial
        call main game
        wait for get stuck 
            call mini game tutorial
            call mini game
            exit mini game
            incurr effects on main game
        at finish
            if timer ran out   
                call losing scene
            if got to finish line
                call winning scene
        
>>>>>>> ClassesNeeded-Jerome
