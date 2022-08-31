
pipeline
{ 
    agent{
        docker{
            image 'mingc/android-build-box'
            args '-u root:root'
        }
    }
    stages{
        stage('Init')
        {
            //check git commit message contains "skip ci" if found don't run the pipeline
             
            steps {
                script {

                    lastCommitInfo = sh(script: "git log -1", returnStdout: true).trim()
                    
                    commitContainsSkip = sh(script: "git log -1 | grep 'skip ci'", returnStatus: true)

                    if(commitContainsSkip == 0) {
                        skippingText = " Skipping Build for *${env.BRANCH_NAME}* branch."
                        currentBuild.result = 'ABORTED'
                        error('BUILD SKIPPED') 
                    }
                }
            }
        }
        stage('build')
        {
            // call fastlane lane for generate apk and uploading to testflight
                 steps{

                    sh "chmod +x gradlew"
                    
                    sh "chmod +x Gemfile"
                    
                    sh "fastlane build --env ${env.BRANCH_NAME}"    //eg. fastlane build --env development
                }
        }
        

    }
    // post {
    //     always {
    //         // delete the workspace

    //         // sh "chmod -R 777 ."
            
    //         // deleteDir() 
    //         steps{
    //             sh "echo post stage ends here"
    //         }
    //     }
    
        
       
    // }

}


