// create a sequence of characters and friends
val df = Seq(("Yoda",             "Obi-Wan Kenobi"),
             ("Anakin Skywalker", "Sheev Palpatine"),
             ("Luke Skywalker",   "Han Solo, Leia Skywalker"),
             ("Leia Skywalker",   "Obi-Wan Kenobi"),
             ("Sheev Palpatine",  "Anakin Skywalker"),
             ("Han Solo",         "Leia Skywalker, Luke Skywalker, Obi-Wan Kenobi, Chewbacca"),
             ("Obi-Wan Kenobi",   "Yoda, Qui-Gon Jinn"),
             ("R2-D2",            "C-3PO"),
             ("C-3PO",            "R2-D2"),
             ("Darth Maul",       "Sheev Palpatine"),
             ("Chewbacca",        "Han Solo"),
             ("Lando Calrissian", "Han Solo"),
             ("Jabba",            "Boba Fett")
            ).toDF("name", "friends")
			
// create a case class
case class Friends(name: String, friends: String)

// create dataset from the dataframe
val friends_ds = df.as[Friends]

// display the dataset
 friends_ds.show()
