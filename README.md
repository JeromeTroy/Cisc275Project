# Cisc275Project
Group Project for CISC 275

Each of us will pull this from the repo, then add your name to this file.
After that push it back up to the repo.  Doing this ensures each of us can pull and push from github.

Names:
Jerome Troy,
Matt Collins,
Evie Boyd



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
        
