module Main where

import Sprockell


prog0 :: [Instruction]
prog0 = [
    Load (ImmValue 2) regF 
    , Jump (Abs 105) 
    , Nop 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute LtE regA regB regA 
    , Push regA 
    , Pop regA 
    , Compute Equal regA reg0 regA 
    , Branch regA (Abs 25) 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Jump (Abs 92) 
    , Nop 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Push regA 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue 2) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 50) regA 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue (-1)) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Pop regB 
    , Pop regA 
    , Compute Sub regA regB regA 
    , Push regA 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 78) regA 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regF 
    , Pop regB 
    , Pop regA 
    , Compute Add regA regB regA 
    , Push regA 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Nop 
    , Load (ImmValue 0) regA 
    , Compute Add regF regA regA 
    , Load (IndAddr regA) regA 
    , Push regA 
    , Load (ImmValue (-3)) regC 
    , Compute Add regF regC regC 
    , Pop regA 
    , Store regA (IndAddr regC) 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regB 
    , Jump (Ind regB) 
    , Nop 
    , Load (ImmValue 1) regA 
    , Push regA 
    , Load (ImmValue 5) regA 
    , Push regA 
    , Load (ImmValue 3) regB 
    , Compute Add regF regB regB 
    , Pop regA 
    , Store regA (IndAddr regB) 
    , Load (ImmValue 4) regB 
    , Compute Add regF regB regF 
    , Load (ImmValue 121) regA 
    , Load (ImmValue (-2)) regB 
    , Compute Add regF regB regB 
    , Store regA (IndAddr regB) 
    , Jump (Abs 2) 
    , Nop 
    , Load (ImmValue (-3)) regB 
    , Compute Add regF regB regB 
    , Load (IndAddr regB) regA 
    , Push regA 
    , Load (ImmValue (-4)) regB 
    , Compute Add regF regB regF 
    , Pop regA 
    , Pop regD 
    , Store regA (IndAddr regD) 
    , Load (DirAddr 1) regA 
    , Push regA 
    , Pop regA 
    , WriteInstr regA numberIO 
    , EndProg 
    ]

main = runWithDebugger (debuggerSimplePrint myShow) [prog0]

