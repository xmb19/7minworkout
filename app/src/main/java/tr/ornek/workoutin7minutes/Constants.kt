package tr.ornek.workoutin7minutes

object Constants {

    fun defaultEgzersizListesi() : ArrayList<ExerciseModel>{
        val egzesizListesi = ArrayList<ExerciseModel>()

        /* --------------------------------------------------------------------- */
        val jumpingJacks = ExerciseModel (
            1,
            "Jumping Jacks",
            R.drawable.ic_jumping_jack,
            false,
            false
                )
        egzesizListesi.add (jumpingJacks)

        /* --------------------------------------------------------------------- */
        val birdDogs = ExerciseModel (
            2,
            "Kuşlar ve Köpekler",
            R.drawable.ic_bird_dogs,
            false,
            false
        )

        egzesizListesi.add (birdDogs)
        /* --------------------------------------------------------------------- */
        val highKness = ExerciseModel (
            3,
            "High Kness",
            R.drawable.ic_high_kness,
            false,
            false
        )
        egzesizListesi.add (highKness)

        /* --------------------------------------------------------------------- */
        val pushUps = ExerciseModel (
            4,
            "Şınav",
            R.drawable.ic_push_up,
            false,
            false
        )
        egzesizListesi.add (pushUps)
        /* --------------------------------------------------------------------- */
        val squat = ExerciseModel (
            5,
            "Squat",
            R.drawable.ic_squat,
            false,
            false
        )
        egzesizListesi.add (squat)
        /* --------------------------------------------------------------------- */
        val wallSit = ExerciseModel (
            6,
            "Duvar Oturuşu",
            R.drawable.ic_wall_sit,
            false,
            false
        )
        egzesizListesi.add (wallSit)
        /* --------------------------------------------------------------------- */
        val plank = ExerciseModel (
            7,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )
        egzesizListesi.add (plank)
        /* --------------------------------------------------------------------- */
        val crissCross = ExerciseModel (
            8,
            "Criss Cross",
            R.drawable.ic_criss_cross,
            false,
            false
        )
        egzesizListesi.add (crissCross)
        /* --------------------------------------------------------------------- */
        val lunges = ExerciseModel (
            9,
            "Lunge Hareketi",
            R.drawable.ic_lunges,
            false,
            false
        )
        egzesizListesi.add (lunges)
        /* --------------------------------------------------------------------- */
        val yanPlank = ExerciseModel (
            10,
            "Yan Plank",
            R.drawable.ic_yan_plank,
            false,
            false
        )
        egzesizListesi.add (yanPlank)
        /* --------------------------------------------------------------------- */
        val tricepsDips = ExerciseModel (
            11,
            "Triceps Dips",
            R.drawable.ic_triceps_dips,
            false,
            false
        )
        egzesizListesi.add (tricepsDips)
        /* --------------------------------------------------------------------- */
        val crunhes = ExerciseModel (
            12,
            "Mekik",
            R.drawable.ic_crunches,
            false,
            false
        )
        egzesizListesi.add (crunhes)



        return egzesizListesi
    }

}