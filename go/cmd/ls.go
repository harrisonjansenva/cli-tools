/*
Copyright © 2025 Harrison Jansen van Beek <harrison@jansenvanbeek.me>

*/
package cmd

import (
	"fmt"

	"github.com/spf13/cobra"
)
var path *string
// lsCmd represents the ls command
var lsCmd = &cobra.Command{
	Use:   "ls",
	Short: "list all entries in directory",
	Long: `This function mimics the functionality of the unix ls command. **Add more detail here.`,
	Run: func(cmd *cobra.Command, args []string) {
		fmt.Println("ls called")
	},
}

func init() {

	path = rootCmd.PersistentFlags().StringP("directory", "d", ".", "set a directory to read all entries in")
	rootCmd.AddCommand(lsCmd)

	// Here you will define your flags and configuration settings.

	// Cobra supports Persistent Flags which will work for this command
	// and all subcommands, e.g.:
	// lsCmd.PersistentFlags().String("foo", "", "A help for foo")

	// Cobra supports local flags which will only run when this command
	// is called directly, e.g.:
	// lsCmd.Flags().BoolP("toggle", "t", false, "Help message for toggle")
}
